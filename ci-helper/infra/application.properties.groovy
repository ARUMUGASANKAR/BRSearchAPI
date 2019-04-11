[
  applicationName: 'searchassemblerservice',
  applicationUrl: "http://search-assembler-service.${env.ENV_STACK}.platform.mnscorp.net",
  tillerNamespace: "bloomreachmigration-dotcom-${env.ENV_STACK}",
  k8sConfigName: "searchassemblerservice-config-${env.ENV_STACK}",
  k8sConfig: "${getVaultSecret(githubTokenId, vaultUrl, "${vaultk8sSecretDataPath}/${env.ENV_STACK}")['config']}",
  dockerRegistryCredentialsId: "${createShortUserPassRecord(githubTokenId, vaultUrl, "${vaultNexusSecretDataPath}/nexus_data", 'nexus-creds')}",
  dockerRegistryUrl: "${getVaultSecret(githubTokenId, vaultUrl, "${vaultNexusSecretDataPath}/nexus_data" )['nexus_docker_url']}",
  acrRegistryUrl: "${getVaultSecret(githubTokenId, vaultUrl, "${vaultNexusSecretDataPath}/acr_data" )['acr_server']}",
  acrRegistryCredentialsId: "${createShortUserPassRecord(githubTokenId, vaultUrl, "${vaultNexusSecretDataPath}/acr_data", 'nexus-creds')}",
  mavenSettingsXml: "${getVaultSecret(githubTokenId, vaultUrl, "${vaultNexusSecretDataPath}/nexus_data")['nexus_settings_xml']}",

  sonarProperties: [
      projectKey: "searchassemblerservice:${env.BRANCH_NAME.split('/').drop(1).join('-').toLowerCase()}",
      projectName: "searchassemblerservice-${env.BRANCH_NAME.split('/').drop(1).join('-').toLowerCase()}",
      projectVersion: '1.0',
      sources: 'src/main'
  ],

  files: [
            ["target/search-assembler-service.jar", "/search-assembler-service.jar"],
            ["ci-helper/infra/dockerfiles/app.Dockerfile", "/Dockerfile"]
        ]
]
