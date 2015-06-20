import emocracy.Channel
import emocracy.User

class BootStrap {

    def init = { servletContext ->

      ['Hungry', 'Beertime'].each {
        def c = new Channel(title:it).save()
        println("channel $it: " + c.errors)
      }

      ['elm', 'madhava'].each {
        def u = new User(username:it).save()
        println("user $it: " +u.errors)
      }

    }
    def destroy = {

    }
}
