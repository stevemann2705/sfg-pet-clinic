package in.stevemann.sfgpetclinic.services;

import in.stevemann.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

}
