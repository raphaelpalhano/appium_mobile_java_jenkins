pipeline {
    agent { label 'windows' }
    options {
        disableConcurrentBuilds()
        skipDefaultCheckout()
        buildDiscarder(logRotator(numToKeepStr: '40'))
        timeout(time: 2, unit: 'HOURS')
    }

    stages {
        stage("Init"){
			steps {
				dir('android-11'){
    				git branch: 'master', credentialsId: '256d1d9d-c96b-4690-ae7f-09306810f9de', url: 'https://steps.everis.com/git/TESTEUDI/everis-mobile-automation-framework-java.git'
    			}
				dir('android-10'){
    				git branch: 'master', credentialsId: '256d1d9d-c96b-4690-ae7f-09306810f9de', url: 'https://steps.everis.com/git/TESTEUDI/everis-mobile-automation-framework-java.git'
    			}
				dir('android-9-tablet'){
    				git branch: 'master', credentialsId: '256d1d9d-c96b-4690-ae7f-09306810f9de', url: 'https://steps.everis.com/git/TESTEUDI/everis-mobile-automation-framework-java.git'
    			}
    			bat "call start appium -a 127.0.0.1 -p 4724 -U emulator-5554"
    			bat "call start appium -a 127.0.0.1 -p 4725 -U emulator-5556"
    			bat "call start appium -a 127.0.0.1 -p 4726 -U emulator-5558"
			}
        }
		stage("Tests"){
	        parallel {
                stage('Android 11') {
                    steps {
                    	dir('android-11'){
	                    	lock('android-11') {
			            		bat """
				            		mvn clean test -Dtest=AndroidTest -Ddevice.url=http://127.0.0.1:4724/wd/hub
				            		"""
			            	}
			            }
                    }
                }
                stage('Android 10') {
                    steps {
                    	dir('android-10'){
	                    	lock('android-10') {
			            		bat """
				            		mvn clean test -Dtest=AndroidTest -Ddevice.url=http://127.0.0.1:4725/wd/hub
				            		"""
			            	}
			            }
                    }
                }
                /*
                stage('Android 9 - Tablet') {
                    steps {
                    	dir('android-9-tablet'){
	                    	lock('android-9-tablet') {
			            		bat """
				            		mvn clean test -Dtest=AndroidTest -Ddevice.url=http://127.0.0.1:4726/wd/hub
				            		"""
			            	}
			            }
                    }
                }
                */
	        }
        }
    }
    post {
        always {
        	archiveArtifacts '**/target/report/html/*.html,**/target/report/html/img/*'
        	junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
	        cucumber fileIncludePattern: '**/cucumber.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}