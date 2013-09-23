package ru.mrgrechkinn.java.webapp.ldap_testing.mapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import ru.mrgrechkinn.java.webapp.ldap_testing.model.User;

public class UserContextMapper implements AttributesMapper {

    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        User user = new User();
        String commonName = (String) attributes.get("cn").get();
        if (commonName != null)
            user.setCommonName(commonName);
        String lastName = (String) attributes.get("sn").get();
        if (lastName != null)
            user.setLastName(lastName);
        String uid = (String) attributes.get("uid").get();
        if (lastName != null)
            user.setUid(uid);
        return user;
    }

}
