class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?(.$format)?" {
      constraints {
        // apply constraints here
      }
    }

    "/api/channels/$userId" {
      controller = 'api'
      action = 'channels'
    }

    "/api/register/$username?" {
      controller = 'api'
      action = 'register'
    }

    "/api/vote/$userId/$channelId/$answer" {
      controller = 'api'
      action = 'vote'
    }

    "/"(view: "/index")
    "500"(view: '/error')
  }
}
