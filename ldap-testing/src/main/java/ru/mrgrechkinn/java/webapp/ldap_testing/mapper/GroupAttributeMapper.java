package ru.mrgrechkinn.java.webapp.ldap_testing.mapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import ru.mrgrechkinn.java.webapp.ldap_testing.model.Group;

public class GroupAttributeMapper implements AttributesMapper {

    @Override
    public Object mapFromAttributes(Attributes arg0) throws NamingException {
        String commonName = (String) arg0.get("cn").get();
        if (commonName.equals("gene_admin")) {
            return Group.ADMIN;
        } else if (commonName.equals("gene_user")) {
            return Group.USER;
        } else {
            return Group.UNKNOWN;
        }
    }

}
