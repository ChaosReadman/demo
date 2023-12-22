//getリクエストに成功したら、引数で渡した処理を実行する関数
function getRequest(url, callback) {
    var header = document.getElementById("csrf").name;
    var token = document.getElementById("csrf").value;
    const req = new XMLHttpRequest();
    req.withCredentials = true;
    req.open("GET", url);
    
    req.onreadystatechange = function () {
        if (req.readyState == XMLHttpRequest.DONE) {
            let status = req.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                callback(req.responseText); //引数で渡された処理を実行
            } else {
                // エラー処理
                alert("HTTP-ERROR: " + status);
            }
        }
    }
    req.setRequestHeader(header, token);
    req.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    req.send();
    }
    
//postリクエストに成功したら、引数で渡した処理を実行する関数
function postRequest(url, postData, callback) {
    var header = document.getElementById("csrf").name;
    var token = document.getElementById("csrf").value;
    
    const req = new XMLHttpRequest();
    req.withCredentials = true;
    req.open("POST", url);
    
    req.onreadystatechange = function () {
        if (req.readyState == XMLHttpRequest.DONE) {
            let status = req.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                callback(); //引数で渡された処理を実行
            } else {
                // エラー処理
                alert("HTTP-ERROR: " + status);
                return;
            }
        }
    };
    req.setRequestHeader(header, token);
    req.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    req.send(postData);
}