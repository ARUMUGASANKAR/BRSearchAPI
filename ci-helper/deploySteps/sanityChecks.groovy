def call(applicationName, applicationUrl) {
    try {
      def sanityCheckResult
      println "Building jq"
      sh "docker build -t jq -f ci-helper/common-dockerfiles/jq.Dockerfile ci-helper/common-dockerfiles/"
      println "Runnung jq container for getting health status"
      println "Application URL: " + applicationUrl + '/health'
      docker.image("jq").inside{
                sanityCheckResult = sh (
                    script: """#!/bin/sh
                              sleep 90
                              output=\$(curl -s -L -X GET https://hlc-category-assembler-service.dev.platform.mnscorp.net/health | jq -r '.status')
                              echo \$output
                            """,
                    returnStdout: true ).trim()
            }
      println "sanityCheckResult: " + sanityCheckResult
      // sanityCheckResult='UP'
      if (sanityCheckResult!='UP') {
          println "sanityCheckResult: " + sanityCheckResult
          currentBuild.result = 'FAILURE'
          System.exit(code)
      }

    } catch(e) {
       println("Something went wrong, Sanity checks haven't passed")
       throw e
    } 
      
   
}

return this 
