//表示の更新に関わる処理をまとめたファイル
//getMsgみたいに、複数ページで使う表示の更新処理をここにまとめてある


//引数で渡した処理を、指定した間隔で実行する関数
function exeLoop(interval, loopFunc){
        // 指定された間隔で実行
        loopFunc();
        setInterval(function () {
            loopFunc();
        }, interval);
}

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


function refreshMsg() {
    //コールバック関数で掲示板の表示を更新
    getRequest("/getmsg", function(response){
        var js = JSON.parse(response);
        var tb = document.getElementById("msgs");
        tb.innerText = "";
    
        js.forEach(element => {
            //element.lastUpdateDateに格納されたタイムスタンプから、ユーザーが見やすい表記に変換して表示
            const date = new Date(element.lastUpdateDate); 
            const showDate = `${date.getFullYear()}/${date.getMonth()+1}/${date.getDate()} ${date.getHours()}時${date.getMinutes()}分${date.getSeconds()}秒`
            
            //DOM操作
            addElm =  `<div class='msgRow'>`
            addElm += `<div>${showDate}</div>`
            addElm += `<div><span>${element.userName}</span> : <span class='saidMsg'>${element.message}</span></div>`
            addElm += `</div>`    
            tb.innerHTML += addElm
        });

        //最新の投稿にスクロールする
        const mf = document.getElementsByClassName("msgform");
        mf[0].scrollTo(0, msgs.scrollHeight);
    })
}