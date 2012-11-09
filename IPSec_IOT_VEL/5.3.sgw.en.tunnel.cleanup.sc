# scenario
scenario interop {
    cleanup_REF2;
    cleanup_REF3;
    cleanup_TEST-TAR-EN;
    cleanup_DUMPER;
}

# syncevent
syncevent finish_test cleanup_REF2, cleanup_REF3, cleanup_TEST-TAR-EN, cleanup_DUMPER;

## TEST-TAR-EN(TEST-TAR-EN_NAME) runs as host
command cleanup_TEST-TAR-EN TEST-TAR-EN {
    sync finish_test;

    execute "TEST-TAR-EN_CMD_SYSCTL_NOT_ACCEPT_RA";
    execute "TEST-TAR-EN_CMD_SYSCTL_NOT_FORWARDING";
    TEST-TAR-EN_CMD_CLEAR_NCE_IF1;
    TEST-TAR-EN_CMD_PRINT_NCE_IF1;
    execute "TEST-TAR-EN_CMD_IFCONFIG_IF1_DOWN";
    execute "TEST-TAR-EN_CMD_IFCONFIG_IF1_LLA_DELETE";
    execute "TEST-TAR-EN_CMD_IFCONFIG_IF1_PREFIX1_GA_DELETE";
    execute "TEST-TAR-EN_CMD_IFCONFIG_IF1_PREFIX1_GA_2_DELETE";
}

command cleanup_DUMPER DUMPER {
    sync finish_test;
    
    execute "killall tcpdump";
}

## REF2_TYPE(REF2_NAME)
command cleanup_REF2 REF2 {
    sync finish_test;
    
    execute "REF2_CMD_STOP_RTADVD";
    execute "REF2_CMD_IFCONFIG_IF1_DOWN";
    execute "REF2_CMD_IFCONFIG_IF1_LLA_DELETE";
    execute "REF2_CMD_IFCONFIG_IF1_PREFIX1_GA_DELETE";
    execute "REF2_CMD_IFCONFIG_IF1_PREFIX1_GA_F_DELETE";
    execute "REF2_CMD_IFCONFIG_IF2_DOWN";
    execute "REF2_CMD_IFCONFIG_IF2_LLA_DELETE";
    execute "REF2_CMD_IFCONFIG_IF2_PREFIX2_GA_DELETE";
    execute "REF2_CMD_IFCONFIG_IF2_PREFIX2_GA_F_DELETE";

    execute "REF2_CMD_SYSCTL_NOT_ACCEPT_RA";
    execute "REF2_CMD_SYSCTL_NOT_FORWARDING";
    execute "REF2_CMD_FLUSH_ROUTE_PREFIX1_IF1";
    execute "REF2_CMD_FLUSH_ROUTE_PREFIX2_IF2";
    execute "REF2_CMD_FLUSH_ROUTE_PREFIX3_IF2";
    REF2_CMD_CLEAR_NCE_IF1;
    REF2_CMD_PRINT_NCE_IF1;
}

## REF3(REF3_NAME) runs as host
command cleanup_REF3 REF3 {
    sync finish_test;

    execute "REF3_CMD_SYSCTL_NOT_ACCEPT_RA";
    execute "REF3_CMD_SYSCTL_NOT_FORWARDING";
    REF3_CMD_CLEAR_NCE_IF1;
    REF3_CMD_PRINT_NCE_IF1;
    execute "REF3_CMD_IFCONFIG_IF1_DOWN";
    execute "REF3_CMD_IFCONFIG_IF1_LLA_DELETE";
    execute "REF3_CMD_IFCONFIG_IF1_PREFIX3_GA_DELETE";
    execute "REF3_CMD_IFCONFIG_IF1_PREFIX3_GA_2_DELETE";
}
