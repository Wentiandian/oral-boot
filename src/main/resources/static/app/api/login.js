function loginApi(data) {
    return $axios({
      'url': '/app/user/login',
      'method': 'post',
      data
    })
  }

function sendMsgApi(data){
    this.$axios({
        'url':'/user/sendMsg',
        'method':'post',
        data
    })
}

function loginoutApi() {
  return $axios({
    'url': '/user/logout',
    'method': 'post',
  })
}

  