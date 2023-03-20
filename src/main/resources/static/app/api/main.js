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

function  getRoleList(roleId){
    return $axios({
        'url': `/sys/common/roleList/${roleId}`,
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

function  getRecord(id){
    return $axios({
        'url': `/app/Bl/info/${id}`,
        'method': 'get',
    })
}


function  getDocList(deptId){
    return $axios({
        'url': `/sys/common/docList/${deptId}`,
        'method': 'get',
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


