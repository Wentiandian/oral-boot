function  save(data){
    return $axios({
        'url':'/app/user/saveUser',
        'method':'post',
        data
    })
}

function  saveGh(data){
    return $axios({
        'url':'/app/gh/save',
        'method':'post',
        data
    })
}

function  getGhInfo(id){
    return $axios({
        'url': `/app/gh/info/${id}`,
        'method': 'get',
    })
}

function  getYyInfo(id){
    return $axios({
        'url': `/app/Yy/info/${id}`,
        'method': 'get',
    })
}

function  getDeptList(){
    return $axios({
        'url': `/sys/common/deptList`,
        'method': 'get',
    })
}

function  getDocList(){
    return $axios({
        'url': `/sys/common/docList`,
        'method': 'get',
    })
}

function  saveYy(data){
    return $axios({
        'url':'/app/Yy/save',
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


