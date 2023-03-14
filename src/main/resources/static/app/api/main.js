function  save(data){
    return $axios({
        'url':'/app/user/saveUser',
        'method':'post',
        data
    })
}

function  saveGhInfo(data){
    return $axios({
        'url':'/app/user/saveUser',
        'method':'post',
        data
    })
}
/*//更新用户信息
function  updateUserMessApi(data){
    return $axios({
        'url': '/user/update',
        'method': 'put',
        data
    })
}*/


