(defproject myogenesis "0.1.0-SNAPSHOT"
  :description "Myogenesis, a fitness tracker"
  :url "https://eagleflow.fi/myogenesis/"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.107"]
                 [reagent "0.5.0"]
                 [figwheel "0.3.7"]]
  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.7"]]
  :clean-targets ^{:protect false} [:target-path "out"]
  :figwheel {:css-dirs ["css"]
             :nrepl-port 7888}
  :cljsbuild {
    :builds [{:id "development"
              :source-paths ["src/myogenesis"]
              :figwheel true
              :compiler {:main myogenesis.core
                         :output-to "out/myogenesis.js"
                         :optimizations :none
                         :source-map true}}
             {:id "production"
              :source-paths ["src/myogenesis"]
              :compiler {:main myogenesis.core
                         :output-to "out/myogenesis.js"
                         :optimizations :advanced}}]})
