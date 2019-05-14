#!/usr/bin/env groovy

node {

    // 'dev' or 'production'
    ENV="${ENV}"

    TAG = "${env.BUILD_TAG}"

    // Ansible playbooks repo URL
    INFRA_URL = "${INFRA_URL}"

    // Jenkins build script repo URL
    BUILD_REPO_URL = "${BUILD_REPO_URL}"

    // expor variable to path to Ansible dir in Infra repo
    WORKDIR='monitoring/ansible/monitoring'

    // clone $BUILD_REPO_URL to dedicated directory ./buildscripts/
    dir('buildscripts') {
        git branch: 'master', credentialsId: 'github', url: "${BUILD_REPO_URL}"
    }

    def provision = load 'buildscripts/monitoring/monitoring-ansible.groovy'

    provision.ansibleVmProvisionValidate("${ENV}")
    provision.ansibleVmProvisionApply("${ENV}")
}