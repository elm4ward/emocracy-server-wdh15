import emocracy.Channel
import emocracy.User

class BootStrap {

    def init = { servletContext ->

      ['Beer-Time?', 'Smoking?', 'Coffee?', 'Hamburger?',  'Meeting?', 'Kicker?'].each {
        def c = Channel.findOrSaveByTitle it
        println("channel $it: " + c.errors)
      }

      ['elm', 'madhava', 'stavros'].each {
        def u = User.findOrSaveByUsername it
        println("user $it: " +u.errors)
      }

    }
    def destroy = {

    }
}
