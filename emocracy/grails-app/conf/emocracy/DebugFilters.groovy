package emocracy

import grails.util.Environment

class DebugFilters {

  def filters = {

    def startTime // this is only working correct for one concurrent request
    // Only in development mode executed
    if (Environment.developmentMode) {
      all(controller:'*', action:'*') {
        before = {
          println "DebugFilters: controllerName:$controllerName actionName:$actionName params:$params"
          startTime = System.currentTimeMillis()

        }
        after = { Map model ->
          //println " - ${(System.currentTimeMillis()) - startTime}s"
        }
        afterView = { Exception e ->
          println " - $actionName: ${(System.currentTimeMillis()) - startTime}ms"

        }
      }
    }
  }
}
