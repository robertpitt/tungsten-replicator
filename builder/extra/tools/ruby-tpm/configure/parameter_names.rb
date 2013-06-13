#!/usr/bin/env ruby
#
# TUNGSTEN SCALE-OUT STACK
# Copyright (C) 2009 Continuent, Inc.
# All rights reserved
#

# Named properties for Tungsten configuration.

# Generic parameters that control the entire installation.
DEPLOYMENT_COMMAND = "deployment_command"
DEPLOYMENT_DATASERVICE = "deployment_dataservice"
DEPLOYMENT_HOST = "deployment_host"
DEPLOYMENT_SERVICE = "service_name"
HOST = "host_name"
IP_ADDRESS = "ip_address"
DSNAME = "local_service_name"
DATASERVICENAME = "dataservice_name"
DATASERVICEALIAS = "dataservice_service_alias"
CLUSTERNAME = "cluster_name"
USERID = "user"
HOME_DIRECTORY = "home_directory"
TARGET_DIRECTORY = "target_directory"
TARGET_BASENAME = "target_basename"
CONFIG_TARGET_BASENAME = "config_target_basename"
PREPARE_DIRECTORY = "prepare_directory"
CURRENT_RELEASE_DIRECTORY = 'current_release_directory'
TEMP_DIRECTORY = "temp_directory"
REMOTE_PACKAGE_PATH = "remote_package_path"
DEPLOY_CURRENT_PACKAGE = "deploy_current_package"
GLOBAL_DEPLOY_PACKAGE_URI = "global_deploy_package_uri"
DEPLOY_PACKAGE_URI = "deploy_package_uri"
HOST_ENABLE_MANAGER = "host_enable_manager"
HOST_ENABLE_REPLICATOR = "host_enable_replicator"
FIXED_PROPERTY_STRINGS = "fixed_property_strings"
SKIPPED_VALIDATION_CLASSES = "skipped_validation_classes"
SKIPPED_VALIDATION_WARNINGS = "skipped_validation_warnings"
CONFIG_FILENAME = "config_filename"
CONFIG_DIRECTORY = "config_directory"
RELEASES_DIRECTORY = "releases_directory"
LOGS_DIRECTORY = "logs_directory"
DIRECTORY_LOCK_FILE = "directory_lock_file"
PROFILE_SCRIPT = "profile_script"
ADDITIONAL_PATH = "additional_path"
SKIP_STATEMAP = "skip_statemap"

STAGING_HOST = "staging_host"
STAGING_USER = "staging_user"
STAGING_DIRECTORY = "staging_directory"

PORTS_FOR_USERS = "ports_for_users"
PORTS_FOR_CONNECTORS = "ports_for_connectors"
PORTS_FOR_MANAGERS = "ports_for_managers"
PORTS_FOR_REPLICATORS = "ports_for_replicators"

# Operating system service parameters.
SVC_INSTALL = "install"
SVC_START = "start"
SVC_REPORT = "start_and_report"
ROOT_PREFIX = "root_command_prefix"
SVC_PATH_REPLICATOR = "svc_path_replicator"
SVC_PATH_MANAGER = "svc_path_manager"
SVC_PATH_CONNECTOR = "svc_path_connector"

# Network control parameters.
HOSTS = "hosts"
WITNESSES = "witnesses"
DATASOURCES =  "datasources"

# Generic replication parameters.
REPL_SERVICES = "repl_services"
REPL_HOSTS = "repl_hosts"
REPL_ROLE = "repl_role"
REPL_RMI_INTERFACE = "repl_rmi_interface"
REPL_RMI_ADDRESS = "repl_rmi_address"
REPL_RMI_PORT = "repl_rmi_port"
REPL_RMI_RETURN_PORT = "repl_rmi_return_port"
REPL_AUTOENABLE = "repl_auto_enable"
REPL_DATASOURCE = 'repl_datasource'
REPL_MASTER_DATASOURCE = "repl_master_datasource"
REPL_MASTER_URI = "repl_master_uri"
REPL_MASTERHOST = "repl_master_thl_host"
REPL_MASTERPORT = "repl_master_thl_port"
REPL_MASTER_PREFERRED_ROLE="repl_master_preferred_role"
REPL_BACKUP_METHOD = "repl_backup_method"
REPL_BACKUP_DUMP_DIR = "repl_backup_dump_directory"
REPL_BACKUP_STORAGE_DIR = "repl_backup_directory"
REPL_BACKUP_RETENTION = "repl_backup_retention"
REPL_BACKUP_AUTO_BACKUP = "repl_backup_auto_backup"
REPL_BACKUP_AUTO_PROVISION = "repl_backup_auto_provision"
REPL_BACKUP_SCRIPT = "repl_backup_script"
REPL_BACKUP_ONLINE = "repl_backup_online"
REPL_BACKUP_COMMAND_PREFIX = "repl_backup_command_prefix"
REPL_BOOT_SCRIPT = "repl_datasource_boot_script"
REPL_BOOT_SERVICE_NAME = "repl_datasource_boot_service_name"
REPL_LOG_TYPE = "repl_log_type"
REPL_LOG_DIR = "repl_thl_directory"
REPL_MONITOR_INTERVAL = "repl_monitor_interval_millisecs"
REPL_JAVA_MEM_SIZE = "repl_java_mem_size"
REPL_JAVA_FILE_ENCODING = "repl_java_file_encoding"
REPL_JAVA_USER_TIMEZONE = "repl_java_user_timezone"
REPL_JAVA_ENABLE_CONCURRENT_GC = "repl_java_enable_concurrent_gc"
REPL_BUFFER_SIZE = "repl_buffer_size"
REPL_RELAY_LOG_DIR = "repl_relay_directory"
REPL_THL_DO_CHECKSUM = "repl_thl_do_checksum"
REPL_THL_LOG_CONNECTION_TIMEOUT = "repl_thl_log_connection_timeout"
REPL_THL_LOG_FILE_SIZE = "repl_thl_log_file_size"
REPL_THL_LOG_FSYNC = "repl_thl_log_fsync"

REPL_DBTYPE = "repl_datasource_type"
REPL_DBHOST = "repl_datasource_host"
REPL_DBPORT = "repl_datasource_port"
REPL_DBLOGIN = "repl_datasource_user"
REPL_DBPASSWORD = "repl_datasource_password"
EXTRACTOR_REPL_DBTYPE = "repl_direct_datasource_type"
EXTRACTOR_REPL_DBHOST = "repl_direct_datasource_host"
EXTRACTOR_REPL_DBPORT = "repl_direct_datasource_port"
EXTRACTOR_REPL_DBLOGIN = "repl_direct_datasource_user"
EXTRACTOR_REPL_DBPASSWORD = "repl_direct_datasource_password"
REPL_DBVERSION = "repl_datasource_version"
REPL_DBTHLURL = "repl_datasource_thlurl"
REPL_DBJDBCURL = "repl_datasource_jdbcurl"
REPL_DBJDBCDRIVER = "repl_datasource_jdbcdriver"
REPL_DBJDBCVENDOR = "repl_datasource_jdbcvendor"
REPL_DBBACKUPAGENTS = "repl_datasource_backupagents"
REPL_DBDEFAULTBACKUPAGENT = "repl_datasource_defaultbackupagent"

REPL_MASTER_LOGDIR = "repl_datasource_log_directory"
EXTRACTOR_REPL_MASTER_LOGDIR = "repl_direct_datasource_log_directory"
REPL_MASTER_LOGPATTERN = "repl_datasource_log_pattern"
EXTRACTOR_REPL_MASTER_LOGPATTERN = "repl_direct_datasource_log_pattern"
REPL_THL_LOG_RETENTION = "repl_thl_log_retention"
REPL_CONSISTENCY_POLICY = "repl_consistency_policy"
REPL_SVC_START = "repl_svc_start"
REPL_SVC_REPORT = "repl_svc_start_and_report"
REPL_SVC_MODE = "repl_svc_mode"
REPL_SVC_CHANNELS = "repl_channels"
REPL_SVC_SERVICE_TYPE = "repl_service_type"
REPL_SVC_THL_INTERFACE = "repl_thl_interface"
REPL_SVC_THL_ADDRESS = "repl_thl_address"
REPL_SVC_THL_PORT = "repl_thl_port"
REPL_SVC_THL_READ_ONLY = "repl_thl_read_only"
REPL_SVC_SHARD_DEFAULT_DB = "repl_svc_shard_default_db"
REPL_SVC_ALLOW_BIDI_UNSAFE = "repl_allow_bidi_unsafe"
REPL_SVC_ALLOW_ANY_SERVICE = "repl_svc_allow_any_remote_service"
REPL_SVC_PARALLELIZATION_TYPE = "repl_svc_parallelization_type"
REPL_SVC_PARALLELIZATION_STORE_CLASS = "repl_svc_parallelization_store_class"
REPL_SVC_PARALLELIZATION_APPLIER_CLASS = "repl_svc_parallelization_applier_class"
REPL_SVC_PARALLELIZATION_EXTRACTOR_CLASS = "repl_svc_parallelization_extractor_class"
REPL_SVC_CONFIG_FILE = "repl_svc_config_file"
REPL_SVC_DYNAMIC_CONFIG = "repl_svc_dynamic_config"
REPL_SVC_APPLIER_CONFIG = "repl_svc_applier_config"
REPL_SVC_EXTRACTOR_CONFIG = "repl_svc_extractor_config"
REPL_SVC_FILTER_CONFIG = "repl_svc_filter_config"
REPL_SVC_BACKUP_CONFIG = "repl_svc_backup_config"
REPL_SVC_EXTRACTOR_FILTERS = "repl_svc_extractor_filters"
REPL_SVC_THL_FILTERS = "repl_svc_thl_filters"
REPL_SVC_APPLIER_FILTERS = "repl_svc_applier_filters"
REPL_SVC_APPLIER_BUFFER_SIZE = "repl_svc_applier_buffer_size"
REPL_SVC_SCHEMA = "repl_svc_schema"
REPL_SVC_TABLE_ENGINE = "repl_svc_table_engine"
REPL_SVC_ENABLE_MASTER_SERVICE_COMMENTS = "repl_svc_enable_master_service_comments"
RELAY_ENABLED = "relay_enabled"
LOG_SLAVE_UPDATES = "log_slave_updates"
SLAVE_PRIVILEGED_UPDATES = "slave_privileged_updates"
PREFETCH_ENABLED = "prefetch_enabled"
PREFETCH_MAX_TIME_AHEAD = "prefetch_max_time_ahead"
PREFETCH_MIN_TIME_AHEAD = "prefetch_min_time_ahead"
PREFETCH_SLEEP_TIME = "prefetch_sleep_time"
PREFETCH_SCHEMA = "prefetch_schema"

BATCH_ENABLED = "batch_enabled"
BATCH_LOAD_TEMPLATE = "batch_load_template"
BATCH_INSERT_TEMPLATE = "batch_insert_template"
BATCH_DELETE_TEMPLATE = "batch_delete_template"

REPL_SVC_CLUSTER_ENABLED = "repl_svc_cluster_enabled"

REPL_PIPELINES = "repl_pipelines"

REPL_SVC_NATIVE_SLAVE_TAKEOVER = "repl_native_slave_takeover"
REPL_DISABLE_RELAY_LOGS = "repl_disable_relay_logs"
EXTRACTOR_REPL_DISABLE_RELAY_LOGS = "repl_disable_direct_relay_logs"

REPL_API = "repl_api"
REPL_API_PORT = "repl_api_port"
REPL_API_HOST = "repl_api_host"
REPL_API_USER = "repl_api_user"
REPL_API_PASSWORD = "repl_api_password"
