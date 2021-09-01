# Create build env and install dependencies
FROM php:8.0-alpine as build
# Get composer
RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer

WORKDIR /var/www/htdocs

COPY composer.json ./
COPY composer.lock ./

RUN composer install --no-dev --no-interaction

# Create production image
FROM alpine:3 as production
# Add application user (will use uid=1000)
RUN adduser -g www-data -D application
# Install packages and PHP extensions
RUN apk add --update-cache \
    apache2 \
    curl \
    gnu-libiconv \
    nano \
    openssl \
    php8 \
    php8-apache2 \
    php8-common \
    php8-curl \
    php8-iconv \
    php8-mbstring \
    php8-opcache \
    php8-openssl \
  && ln -s /usr/bin/php8 /usr/bin/php \
  && rm -rf /var/cache/apk/*
# Disable Apache default port
RUN sed -i 's/Listen 80/# Listen 80/g' /etc/apache2/httpd.conf
# enable apache rewrite module
RUN sed -i 's+#LoadModule rewrite_module modules/mod_rewrite.so+LoadModule rewrite_module modules/mod_rewrite.so+g' /etc/apache2/httpd.conf
# Redirect logging
RUN ln -s /dev/stderr /var/log/apache2/error.log \
  && ln -s /dev/stdout /var/log/apache2/access.log \
  && chown -R application:www-data /var/log/apache2 \
  && chown -R application:www-data /run/apache2/

WORKDIR /var/www/htdocs

# Add server config
COPY server.conf /etc/apache2/conf.d/default.conf

USER application

# Copy dependencies and source code
COPY --chown=application:www-data --from=build /var/www/htdocs/vendor ./vendor
COPY --chown=application:www-data src/ ./

CMD ["httpd", "-D", "FOREGROUND"]
