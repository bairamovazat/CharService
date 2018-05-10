function main() {
    var messagesBlock = document.getElementById("messagesList");
    var messageSubmitBtn = document.getElementById("messageSubmitBtn");
    var messageForm = document.getElementById("messageForm");
    function waitNewMessages() {
        var xhr = new XMLHttpRequest();
        var url = "/chat/" + chatId + "/updates/?lastMessageId=" + lastMessageId;
        xhr.open("GET", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4) return;

             if (xhr.status != 200) {
                 console.log(xhr.status + ': ' + xhr.statusText);
             } else {
                 console.log(xhr.responseText);
             }

            if (xhr.responseText.length > 0) {
                console.log("response text length > 0");
                var messages = JSON.parse(xhr.responseText);
                putMessagesToDialog(messages);
            }
            waitNewMessages();
        };
        xhr.send();
    }

    function putMessagesToDialog(messages) {
        console.log(messages);
        messages.forEach(function (message) {
           if(message["id"] > lastMessageId){
               console.log(message["id"]);
               lastMessageId = message["id"];
           }
           console.log(message);
           var messageElement = getMessageElementBy(message["id"], message["user"]["name"], message["sendDate"], message["text"]);
           messagesBlock.insertBefore(messageElement, messagesBlock.firstChild);
        });
    }

    function getMessageElementBy(id, userName, sendDate, text) {
        var message = document.createElement("div");
        message.setAttribute("class", "card-text mb-3");
        message.setAttribute("id", "message-" + id);
        var date = new Date(sendDate);
        message.innerHTML = " <small class=\"card-text text-left\">\n" +
            "         " + userName + "\n" +
            "         </small>\n" +
            "         <small class=\"card-text text-muted float-right\">\n" +
            "         " + date.toString('dd.MM.yyyy hh:mm:ss') + "\n" +
            "         </small>\n" +
            "         <div class=\"card-text\">\n" +
            "         " + text + "\n" +
            "         </div>";
        return message;
    }

    messageSubmitBtn.onclick = function() {
        if(messageForm["message"].value.length === 0){
            return false;
        }
        sendMessage(chatId, messageForm["message"].value);
        messageForm.reset();
        return false;
    };

    function sendMessage(chatId, message) {
        var xhr = new XMLHttpRequest();
        var url = "/chat/send/";
        xhr.open("POST", url, true);
        xhr.setRequestHeader('Content-Type', 'application/json;');
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4) return;

            if (xhr.status != 200) {
                console.log(xhr.status + ': ' + xhr.statusText);
            } else if (xhr.responseText.length > 0) {
                console.log(xhr.responseText);
            }
        };
        xhr.send(JSON.stringify({"chatId":chatId, "message":message}));
    }
    waitNewMessages();
}
main();