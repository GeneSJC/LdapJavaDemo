package ru.mrgrechkinn.java.webapp.ldap_testing.service;

import java.util.List;

import javax.naming.directory.SearchControls;

import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import ru.mrgrechkinn.java.webapp.ldap_testing.mapper.UserContextMapper;
import ru.mrgrechkinn.java.webapp.ldap_testing.model.User;

public class AuthenticationService {

    private LdapTemplate ldapTemplate;

    public AuthenticationService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public boolean login(String userName, String password) {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person")).and(new EqualsFilter("uid", userName));
        return ldapTemplate.authenticate(DistinguishedName.EMPTY_PATH, filter.toString(), password);
    }
    

    public List<User> getAllUsers() {
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        return ldapTemplate.search("", "(objectclass=person)", controls, new UserContextMapper());
    }

}
