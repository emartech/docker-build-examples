# Create build env and install dependencies
FROM php:7.4-alpine as build
# Get composer
RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --version=2.1.3 --filename=composer

WORKDIR /var/www/htdocs

COPY composer.json ./

RUN composer install --no-dev --no-interaction

# Create production image
FROM php:7.4-alpine as production
# Install packages and PHP extensions
RUN apk add --update-cache apache2 php7-apache2 \
  && docker-php-ext-install opcache \
  # others
  && rm -rf /var/cache/apk/*

# Redirect logging
RUN ln -s /dev/stderr /var/log/apache2/error.log \
  && ln -s /dev/stdout /var/log/apache2/access.log \
  && chown -R www-data:www-data /var/log/apache2 \
  && chown -R www-data:www-data /run/apache2/

WORKDIR /var/www/htdocs

# Add server config
COPY server.conf /etc/apache2/conf.d/server.conf

USER www-data

# Copy dependencies and source code
COPY --chown=www-data:www-data --from=build /var/www/htdocs/vendor ./vendor
COPY --chown=www-data:www-data src/ ./

CMD ["httpd", "-D", "FOREGROUND"]