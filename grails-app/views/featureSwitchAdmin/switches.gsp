<html>
<head>
    <title>Feature Switches Admin Page</title>
    <meta name="layout" content="${grailsApplication.config.featuresConfig.adminLayout ?: 'main'}"/>
    <asset:stylesheet src='feature-switch'/>
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
