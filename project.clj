(defproject cheshire-cat "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 ;; saber qu� middleware aplicar en una aplicaci�n Ring
                 ;; y en qu� orden, es dif�cil. ring-defaults sirve
                 ;; para automatizar el proceso
                 [ring/ring-defaults "0.1.5"]
                 ;; chesire es una librer�a para codificar y decodificar JSON
                 [cheshire "5.4.0"]
                 ;; ring-json es una librer�a para automatizar la codificaci�n
                 ;; y decodificaci�n de JSON dentro de ring (si se usa esta
                 ;; librer�a no hace falta entonces usar cheshire)
                 [ring/ring-json "0.4.0"]
                 ]
;; The important parts here are :plugins and :ring
;; The lein-plugin provides automation for Ring tasks

  :plugins [[lein-ring "0.9.7"]]

;; The :ring :handler keys tells the startup web server task where to find the main app routes. Here it points to the app
  :ring {:handler cheshire-cat.handler/app}

  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
