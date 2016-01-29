(ns cheshire-cat.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            ;; En vez de Cheshire usamos ring.middleware.json
            ;; y ring.util.response
            [ring.middleware.json :as ring-json]
            [ring.util.response :as rr]
            ))

;; defroutes se usa para crear una secuencia de rutas HTTP routes. La hemos 
;; llamado app-routes en este caso. Estos son los request paths de la aplicación
;; Es un handler.

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/cheshire-cat" []
       ;; A continuación hay que poner una respuesta en forma de mapa
       ;; con response sólo tenemos que indicar el body, el status es
       ;; por defecto 200 y no hay headers
       (rr/content-type (rr/response {:name "Cheshire Cat" :status :grinning})"application/json; charset=utf-8"))
  (route/not-found "Not Found"))

;; app is the main entry point into our application
(def app
  ;; -> inserta app-routes en la segunda posición, es decir, entre wrap-defaults
  ;; y site-defaults, como lo teníamos antes, pero de esta forma, el resultado
  ;; de eso, que es un mapa, de lo pasamos directamente a la segunda expresión
  ;; que lo traduce a json
  (-> app-routes
   (wrap-defaults site-defaults)
   (ring-json/wrap-json-response)))




;; ------------------------------------------------------------------------------
;; ;; A continuación se describe el proceso de forma manual sin usar la ring.json library

;; (ns cheshire-cat.handler
;;   (:require [compojure.core :refer :all]
;;             [compojure.route :as route]
;;             [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
;;             [chesire.core :as json]
;;             ))

;; ;; defroutes se usa para crear una secuencia de rutas HTTP routes. La hemos 
;; ;; llamado app-routes en este caso. Estos son los request paths de la aplicación
;; ;; Es un handler.
;; (defroutes app-routes
;;   (GET "/" [] "Hello World")
;;   ;; Definimos una segunda ruta /cheshire-cat
;;   ;; Tanto los request como las respuestas son maps.
;;   ;; Normalmente hay de describir, al menos :status, :headers y :body
;;   (GET "/cheshire-cat" []
;;        {:status 200
;;         ;; En headers le indicamos que el tipo de respuesta es json
;;         :headers {"Content-Type" "application/json; charset=utf-8"}
;;         ;; En body ponemos el cuerpo de la respuesta. Queremos que sea json,
;;         ;; pero para que sea más fácil, lo escribimos como un mapa de clojure
;;         ;; y le aplicamos generate-string para convertirlo a json
;;         :body {(json/generate-string {:name "Cheshire-cat" :status :grinning})}})
;;   (route/not-found "Not Found"))

;; ;; app is the main entry point into our application
;; (def app
;;   ;; wrap-defaults automatiza la configuración de la aplicación
;;   ;; app-routes es el handler al que le vamos a aplicar esa configuración
;;   ;; site-defaults es una configuración provista por el propio middleware
;;   ;; que configura automáticamente cuestiones como parámetros, cookies,
;;   ;; sesiones, recursos estáticos, etc
;;   (wrap-defaults app-routes site-defaults))
