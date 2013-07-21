<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Feature Switches Admin Page</title>
  <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'feature-switch.css')}" />
</head>
<body>

    <h1>Features</h1>

    <div class="notification">
        <b>Note:</b> Feature toggling is applicable only for the running instance. Feature state is not persisted beyond the lifetime of the application.
    </div>

    <ul>
        <g:each in="${features}" var="name, enablement">
            <g:form action="toggle">
                <g:hiddenField name="feature" value="${name}" />
                <li class="feature ${enablement ? 'switched-on' : 'switched-off'}">
                    <span class="label">${name}</span>
                    <button class="toggle">toggle</button>
                </li>
            </g:form>
        </g:each>
    </ul>

</body>
</html>
