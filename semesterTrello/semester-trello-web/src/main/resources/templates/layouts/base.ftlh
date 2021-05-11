<#macro main title css=[] scripts=[]>
    <!DOCTYPE html>
    <html lang="ru" xmlns="http://www.w3.org/1999/html">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/css/materialize.min.css">
        <link rel="stylesheet" href="/css/main.css" />
    </head>
    <body>
    <header class="header-section">
        <nav>
            <div class="nav-wrapper">
                <a href="/" class="brand-logo">Semester Trello</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <#if !user??>
                        <li><a href="/signIn">Sign In</a></li>
                        <li><a href="/signUp">Sign Up</a></li>
                        <#else>
                            <li><a href="/logout">Logout</a></li>
                            <li><a href="/profile">Profile</a></li>
                    </#if>
                    <li><a href="/chat">Chat</a></li>
                    <li><a href="/map">Map</a></li>
                    <li><span id="userCount"></span></li>
                </ul>
            </div>
        </nav>
    </header>
    <#nested>
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/materialize.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/js/main.js"></script>
    <script src="/js/userCount.js"></script>
    </body>
    </html>
</#macro>