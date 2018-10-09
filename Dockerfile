FROM clojure:lein-2.7.1-alpine
MAINTAINER Scott Bauer <sbauer322@gmail.com>

# To avoid pulling dependencies everytime, we create the directory below, run lein deps,
# and then use those dependencies until something changes.
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY project.clj /usr/src/app/
RUN lein deps
COPY . /usr/src/app

RUN lein uberjar
ADD target/piglet.jar /piglet/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/piglet/app.jar"]