/**
 * Copyright 2018 Mike Hummel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mhus.cherry.vault.core;

import java.util.List;

import aQute.bnd.annotation.component.Component;
import de.mhus.cherry.vault.api.model.VaultArchive;
import de.mhus.cherry.vault.api.model.VaultEntry;
import de.mhus.cherry.vault.api.model.VaultGroup;
import de.mhus.cherry.vault.api.model.VaultKey;
import de.mhus.cherry.vault.api.model.VaultTarget;
import de.mhus.cherry.vault.core.impl.StaticAccess;
import de.mhus.lib.adb.Persistable;
import de.mhus.lib.basics.Ace;
import de.mhus.lib.errors.MException;
import de.mhus.lib.xdb.XdbService;
import de.mhus.osgi.sop.api.aaa.AaaContext;
import de.mhus.osgi.sop.api.adb.AbstractDbSchemaService;
import de.mhus.osgi.sop.api.adb.DbSchemaService;
import de.mhus.osgi.sop.api.adb.ReferenceCollector;

@Component(immediate=true,provide=DbSchemaService.class)
public class CherryVaultManager extends AbstractDbSchemaService {

	private XdbService service;

	@Override
	public void registerObjectTypes(List<Class<? extends Persistable>> list) {
		list.add(VaultGroup.class);
		list.add(VaultTarget.class);
		list.add(VaultEntry.class);
		list.add(VaultArchive.class);
		list.add(VaultKey.class);
	}

	@Override
	public void doInitialize(XdbService dbService) {
		this.service = dbService;
		StaticAccess.moManager = this;
	}

	@Override
	public void doDestroy() {
		StaticAccess.moManager = null;
	}

	@Override
	public void collectReferences(Persistable object, ReferenceCollector collector) {
		
	}

	@Override
	public void doCleanup() {
		
	}

	public XdbService getManager() {
		return service;
	}

	@Override
	public boolean canCreate(AaaContext context, Persistable obj) throws MException {
		return true;
	}

	@Override
	public void doPostInitialize(XdbService manager) throws Exception {
		
	}

	@Override
	protected String getAcl(AaaContext context, Persistable obj) throws MException {
		return "*=" + Ace.RIGHTS_ALL; // TODO
	}

}