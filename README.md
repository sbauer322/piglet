# piglet

_ortsnay! oinkway!_

piglet is a microservice to convert standard English strings into pig latin.

Prerequisites

You'll need the following installed in order to build piglet:

    - Clojure 1.9
    - Leinigen 2.5+

## Development

A simple `lein run` will do the trick to launch the service. The default address is `localhost:3000`.

Configurations are stored in `env/dev/resources/config.edn` and `env/dev/prod/config.edn` for the respective profiles. All sensitive config should be untracked and located elsewhere. See `yogthos/config` and `environ` for more information.

A certain profile can be specified - for the dev profile `lein with-profile dev run`.

## Production

    lein uberjar
    java -jar piglet.jar [args]

With an uberjar, an additional config file can be passed in via something like the following snippet for the Java command: `-Dconfig="config.edn"`. This will merge and overwrite with the default config values.

## API

> Note the project is under active development and may experience sudden breaking changes between commits

`api/v1.0/pig-latin` expects a json body as part of the `POST` request consisting of:

    {
        "msg": "your phrase goes here"
    }
    
and responds with:

    {
      "original": "your phrase goes here",
      "translation": "ouryay asephray oesgay erehay"
    }
