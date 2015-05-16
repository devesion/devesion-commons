package com.devesion.commons.utils.types;

import com.devesion.commons.annotations.Immutable;
import com.devesion.commons.annotations.ddd.ValueObject;
import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * Implements Unit of Work pattern.
 */
public class UnitOfWork<T> {

	private final List<WorkEntry<T>> entries = Lists.newLinkedList();

	public void registerOrder(T object) {
		addEntry(object, WorkEntry.WorkType.ORDER);
	}

	public void registerPut(T object) {
		addEntry(object, WorkEntry.WorkType.PUT);
	}

	public void registerUpdate(T object) {
		addEntry(object, WorkEntry.WorkType.UPDATE);
	}

	public void registerDeletion(T object) {
		addEntry(object, WorkEntry.WorkType.DELETE);
	}

	public void registerRecursiveDeletion(T object) {
		addEntry(object, WorkEntry.WorkType.RECURSIVE_DELETE);
	}

	public void registerCommit(T object) {
		addEntry(object, WorkEntry.WorkType.COMMIT);
	}

	private void addEntry(T object, WorkEntry.WorkType type) {
		entries.add(new WorkEntry<>(object, type));
	}

	public List<WorkEntry<T>> getEntries() {
		return entries;
	}

	public List<WorkEntry<T>> getEntriesAndReset() {
		List<WorkEntry<T>> retEntries = Lists.newArrayList(entries);
		entries.clear();
		return retEntries;
	}

	/**
	 * Represents single operation.
	 */
	@ValueObject
	@Immutable
	@EqualsAndHashCode
	public static final class WorkEntry<T> {

		@Getter
		private final T object;

		@Getter
		private final WorkType type;

		WorkEntry(T object, WorkType type) {
			this.object = object;
			this.type = type;
		}

		/**
		 * Mutation type.
		 */
		public static enum WorkType {
			ORDER, PUT, UPDATE, DELETE, RECURSIVE_DELETE, COMMIT, ROLLBACK, UNKNOWN
		}
	}
}