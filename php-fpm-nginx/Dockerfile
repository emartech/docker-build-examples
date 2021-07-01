FROM php:7.4-alpine AS build

ENV COMPOSER_VERSION=2.1.3

RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --version=$COMPOSER_VERSION --filename=composer

COPY composer.json ./
COPY composer.lock ./

RUN composer install --no-dev --no-interaction

FROM php:7.4-fpm-alpine AS production

USER www-data

WORKDIR /var/www/htdocs

COPY --chown=www-data:www-data --from=build /vendor ./vendor
COPY --chown=www-data:www-data src/ ./
