package in.ashwanthkumar.gocd.github.provider.git;

import in.ashwanthkumar.gocd.github.settings.scm.DefaultScmPluginConfigurationView;
import in.ashwanthkumar.gocd.github.util.BranchFilter;
import in.ashwanthkumar.gocd.github.util.FieldFactory;
import in.ashwanthkumar.gocd.github.util.field.PartOfIdentity;
import in.ashwanthkumar.gocd.github.util.field.Required;
import in.ashwanthkumar.gocd.github.util.field.Secure;

import java.util.Map;

public class GitScmPluginConfigurationView extends DefaultScmPluginConfigurationView {

    public static final String BRANCH_BLACKLIST_PROPERTY_NAME = "branchblacklist";
    public static final String BRANCH_WHITELIST_PROPERTY_NAME = "branchwhitelist";

    @Override
    public String templateName() {
        return "/views/scm.template.branch.filter.html";
    }

    @Override
    public Map<String, Object> fields() {
        Map<String, Object> fields = super.fields();
        fields.put(BRANCH_WHITELIST_PROPERTY_NAME,
                FieldFactory.createForScm("Whitelisted branches", "", PartOfIdentity.YES, Required.NO, Secure.NO, 4));
        fields.put(BRANCH_BLACKLIST_PROPERTY_NAME,
                FieldFactory.createForScm("Blacklisted branches", "", PartOfIdentity.YES, Required.NO, Secure.NO, 5));
        return fields;
    }

    @Override
    public BranchFilter getBranchFilter(Map<String, String> configuration) {
        String blacklist = configuration.get(BRANCH_BLACKLIST_PROPERTY_NAME);
        String whitelist = configuration.get(BRANCH_WHITELIST_PROPERTY_NAME);

        return new BranchFilter(blacklist, whitelist);
    }

}
