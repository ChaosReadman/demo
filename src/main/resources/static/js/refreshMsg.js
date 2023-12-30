//掲示板の投稿内容の表示を更新
function refreshTbl(tjs) {
    var js = JSON.parse(tjs);
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
}

function refreshMsg() {
    var header = document.getElementById("csrf").name;
    var token = document.getElementById("csrf").value;
    const url = "/members/getmsg";
    const req = new XMLHttpRequest();
    req.withCredentials = true;
    req.open("GET", url);

    req.onreadystatechange = function () {
        if (req.readyState == XMLHttpRequest.DONE) {
            let status = req.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                refreshTbl(req.responseText);
                var  mf = document.getElementsByClassName("msgform");

                mf[0].scrollTo(0, msgs.scrollHeight);
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