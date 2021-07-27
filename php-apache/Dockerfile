# Create build env and install dependencies
FROM php:7.4-alpine as build
# Get composer
RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --version=2.1.3 --filename=composer

WORKDIR /var/www/htdocs

COPY composer.json ./

RUN composer install --no-dev --no-interaction

# Create production image
FROM php:7.4-alpine as production
# Add application user (will use uid=1000)
RUN adduser -g www-data -D application
# Install packages and PHP extensions
RUN apk add --update-cache apache2 php7-apache2 \
  && docker-php-ext-install opcache \
  # others
  && rm -rf /var/cache/apk/*
# Disable Apache default port
RUN sed -i 's/Listen 80/# Listen 80/g' /etc/apache2/httpd.conf
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