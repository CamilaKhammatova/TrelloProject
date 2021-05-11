<#import "layouts/base.ftlh" as base>
<@base.main title="Sign in">
    <div class="container signUpContainer">
        <div class="row">
            <form class="col s12" action="/signIn" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row">
                    <div class="input-field col s6">
                        <input type="email" name="email" id="email" class="validate" required>
                        <label for="email">Email</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="password" name="password" id="password" class="validate" required>
                        <label for="password">Password</label>
                    </div>
                </div>
                <div class="row">
                    <button class="btn waves-effect waves-light" type="submit" name="action">Submit</button>
                </div>
            </form>
            <p><a href="/oauth2/authorization/google" class="text-accent-1">Enter via Google Account</a></p>
        </div>
    </div>
</@base.main>
