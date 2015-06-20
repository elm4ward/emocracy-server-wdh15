package emocracy

import grails.converters.JSON
import grails.transaction.Transactional
import org.joda.time.LocalDateTime

@Transactional
class ChannelService {

  /*
   {
      "name": "Hungry?",
      "id": 2,
      "yes": 10,
      "no": 1,
      "alive": 1,
      "democracy": 1
    }
   */
  def getAll() {
    int majoritySize = User.count / 2
    def channels = Channel.list().collect { Channel channel ->
      def limit = new LocalDateTime().minusMinutes(5)
      def recents = channel.votes.findAll { it.submitted.isAfter(limit) }
      def alive = recents?.size() > 0
      def yes = recents.findAll { it.answer == 1 }?.size()
      def no = recents.findAll { it.answer != 1 }?.size()
      def democracy = null
      if (yes >= majoritySize ){
        democracy = 1
      }
      if (no >= majoritySize ){
        democracy = 0
      }
      [
          name     : channel.title,
          id       : channel.id,
          yes      : yes,
          no       : no,
          alive    : alive ? 1 : 0,
          democracy: democracy
      ]
    }
    println channels
    channels
  }

  def vote(Long userId, Long channelId, Long answer){
    def channel = Channel.get channelId
    def user = User.get userId
    def vote = Vote.findRecentByChannelAndUser(channelId, userId).list()
    if(vote || !user || !channel){
      return false
    }
    vote = new Vote(submitted: new LocalDateTime(), answer:answer)
    vote.user = user
    channel.addToVotes(vote)
    vote.save()
    println(vote.errors)
    channel.save()
    true
  }
}
