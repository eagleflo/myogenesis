Myogenesis
==========

Myogenesis is a web app for tracking barbell workouts.

Originally created for a presentation about ClojureScript, React and
Reagent. (The standard TodoMVC example is getting really boring.)

Besides being better than Fitocracy for my needs, it aims to show that
starting a new Reagent project from scratch is not complicated. At the
time of writing, creating a new Reagent project with `lein new reagent
project` comes with many unnecessary bells & whistles.


Development
-----------

Run `lein figwheel` and open `index.html`.

An instance of nREPL will be running in port 7888.


Building
--------

Run `lein cljsbuild once production`.
