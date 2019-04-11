 def call(image, workDir='') {
  def installed = false;
  def p = load("ci-helper/infra/${workDir}/application.properties.groovy")
  writeSecretFileInJenkins(p.k8sConfig, p.k8sConfigName)
  withCredentials([file(credentialsId: p.k8sConfigName, variable: 'KUBECONFIG')]) {
    withEnv(["KUBECONFIG=${KUBECONFIG}", "HELM_HOME=${WORKSPACE}"]) {    
        docker.withRegistry(p.acrRegistryUrl, p.acrRegistryCredentialsId) {
             docker.image("helm-kubectl").inside() {
             sh("cd ci-helper/infra/${workDir}/helm-${p.applicationName}/; helm lint; cd -")
             installed = sh (script: "helm ls --tiller-namespace=bloomreachmigration-dotcom-${env.ENV_STACK}", returnStdout: true).find(/${p.applicationName}-${env.ENV_STACK}/)
             if (!installed){ 
                sh(returnStdout: true, script: """
                   helm init --client-only; 
                   helm install ci-helper/infra/${workDir}/helm-${p.applicationName} --name=${p.applicationName}-${env.ENV_STACK} \
                   --set dockerImage=${p.acrRegistryUrl.split('/')[2]}/${image} \
                   --values=ci-helper/infra/${workDir}/helm-${p.applicationName}/values_${env.ENV_STACK}.yaml --debug \
                   --tiller-namespace=bloomreachmigration-dotcom-${env.ENV_STACK};
                   """) 
              } else {
                sh(returnStdout: true, script: """
                   cd ci-helper/infra/${workDir}/helm-${p.applicationName}; 
                   helm upgrade ${p.applicationName}-${env.ENV_STACK} . \
                   --set dockerImage=${p.acrRegistryUrl.split('/')[2]}/${image} \
                   --values=values_${env.ENV_STACK}.yaml --debug \
                   --tiller-namespace=bloomreachmigration-dotcom-${env.ENV_STACK};
                   cd -; """)}
              } //image
         } //registry     
    }//env
  }//cred
}//call
 
return this
