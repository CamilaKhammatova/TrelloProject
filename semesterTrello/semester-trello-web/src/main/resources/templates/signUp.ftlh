<#import "spring.ftl" as spring/>
<#import "layouts/base.ftlh" as base>
<@base.main title="Sign up">
    <div class="container signUpContainer">
        <div class="row">
            <@spring.bind "signUpForm"/>
            <form class="col s12" action="/signUp" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row">
                    <div class="input-field col s6">
                        <@spring.formInput "signUpForm.firstname" "id='first_name' class='validate' required"/>
                        <@spring.showErrors "<br>" "error"/>
                        <label for="first_name">Firstname</label>
                    </div>
                    <div class="input-field col s6">
                        <@spring.formInput "signUpForm.email" "id='email' class='validate' required" "email"/>
                        <@spring.showErrors "<br>" "error"/>
                        <label for="email">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <@spring.formPasswordInput "signUpForm.password" "id='password' class='validate' required "/>
                        <@spring.showErrors "<br>" "error"/>
                        <#if difficultError??><span class="error">${difficultError}</span></#if>
                        <#if matchingError??><span class="error">${matchingError}</span></#if>
                        <label for="password">Password</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <@spring.formPasswordInput "signUpForm.repeatPassword" "id='repeatePassword' class='form-control' required"/>
                        <#if matchingError??><span class="error">${matchingError}</span></#if>
                        <label for="repeatPassword">Repeat password</label>
                    </div>
                </div>
                <div class="row">
                    <button class="btn waves-effect waves-light" type="submit" name="action">Submit</button>
                </div>
            </form>
        </div>
    </div>
</@base.main>