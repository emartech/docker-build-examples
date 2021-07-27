FROM ruby:2.7.4-alpine3.14 as build

RUN apk update \
    && apk add build-base

COPY --chown=root Gemfile Gemfile.lock ./

RUN gem install bundler \
    && chmod -R 777 "$GEM_HOME" \
    && bundle install --jobs 4 --retry 3

FROM ruby:2.7.4-alpine3.14

WORKDIR /home/ruby/app

COPY --from=build /usr/local/bundle /usr/local/bundle
COPY /app .

CMD ["ruby", "inline-rails.rb"]
