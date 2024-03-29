package io.github.supercube.web.filter.reactive;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * This filter is used in production, to put HTTP cache headers with a long expiration time.
 */
public class CachingHttpHeadersFilter implements WebFilter {

    private final long cacheTimeToLive;

    /**
     * <p>Constructor for CachingHttpHeadersFilter.</p>
     *
     * @param cacheTimeToLive a {@link java.lang.Long} object.
     */
    public CachingHttpHeadersFilter(Long cacheTimeToLive) {
        this.cacheTimeToLive = cacheTimeToLive;
    }

    /** {@inheritDoc} */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return ServerWebExchangeMatchers.pathMatchers("/i18n/**", "/content/**", "/app/**")
            .matches(exchange)
            .filter(ServerWebExchangeMatcher.MatchResult::isMatch)
            .doOnNext(matchResult -> {
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().setCacheControl("max-age=" + cacheTimeToLive + ", public");
                response.getHeaders().setPragma("cache");
                response.getHeaders().setExpires(cacheTimeToLive + System.currentTimeMillis());

            })
            .then(Mono.defer(() -> chain.filter(exchange)));
    }
}

