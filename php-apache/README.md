# Apache server with PHP

* This example will by default start Apache web server in foreground. You may want to tweak the `server.conf` for your specific needs.
* If you want to run a PHP script directly (job, worker) override the command via `gap.json` like `php /var/www/htdocs/job.php`.
* Some PHP extensions can be installed via [`docker-php-ext-install`](https://github.com/mlocati/docker-php-extension-installer#supported-php-extensions), others by `apk` or `pecl`. You can also use `docker-php-ext-enable/disable` to turn extensions on or off.