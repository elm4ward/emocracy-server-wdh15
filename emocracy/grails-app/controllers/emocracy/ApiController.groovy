package emocracy

import grails.converters.JSON

class ApiController {

  def channelService

  def index() {
    def api = [calls: [
        [name:'channels', url: createLink(action: 'channels', absolute: true), info: "call by appending id of user as url path component"],
        [name:'register', url: createLink(action: 'register', absolute: true), info: "call by appending name of user as url path component"],
        [name:'vote',url: createLink(action: 'vote', absolute: true), info: "call by appending id of user and id of channel as url path component + 1 or 0 for answer type"]

    ]
    ]
    render(api as JSON)
  }

  def register(String username) {
    def user = User.findOrSaveByUsername username
    if (user.hasErrors()) {
      render status: 400
      return
    }
    render(user as JSON)
  }

  def channels(Long userId) {
    def user = User.get userId
    if (!user) {
      render status: 400
      return
    }
    render([channels: channelService.getAll()] as JSON)
  }

  def vote(Long userId, Long channelId, Long answer) {
    def result = channelService.vote(userId, channelId, answer)
    println("vote result: $result")
    render status: 200
  }
}
