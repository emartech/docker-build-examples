# PHP FPM with NGINX proxy

This setup is recommended if you serve a lot of static files (html, image, js, css) in your web service. This is where NGINX overperforms Apache. If you operate an API service only, this just adds unnecessary complexity.

* In this example start the PHP FPM and Nginx containers in one pod.
* Nginx proxy is needed for web deployments only.
* Source code is copy to shared volume.
* The needed patch is in [gap folder](gap/).
