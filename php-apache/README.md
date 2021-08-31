# Apache server with PHP

* This example will by default start Apache web server in foreground. You may want to tweak the `server.conf` or the `.htaccess` for your specific needs.
* If you want to run a PHP script directly (job, worker) override the command via `gap.json` like `php /var/www/htdocs/job.php`.
* PHP extensions can be installed via [`apk add`](https://pkgs.alpinelinux.org/packages?name=php8*&branch=edge&arch=x86_64).
