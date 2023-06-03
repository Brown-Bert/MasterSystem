FROM nginx
MAINTAINER yzx
ENV RUN_USER nginx
ENV RUN_GROUP nginx
ENV DATA_DIR /data/dist
ENV LOG_DIR /data/log/nginx
RUN mkdir /data/log/nginx -p
RUN chown nginx.nginx -R /data/log/nginx
ADD dist /data/dist
ADD default.conf /etc/nginx/conf.d/default.conf
ENV TimeZone = Asia/Shanghai
EXPOSE 80
ENTRYPOINT nginx -g "daemon off;"
