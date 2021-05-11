<#import "layouts/base.ftlh" as base>
<@base.main title="Profile">
    <div class="container">
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Firstname</th>
                <th>Email</th>
            </tr>
            </thead>

            <tbody>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstname}</td>
                    <td>${user.email}</td>
                </tr>
            </tbody>
        </table>

    </div>
</@base.main>

