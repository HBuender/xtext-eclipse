/*******************************************************************************
 * Copyright (c) 2011 Sigasi NV (http://www.sigasi.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Lieven Lemiengre - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.xtext.builder.resourceloader;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.common.collect.Lists;
import com.google.inject.ImplementedBy;

/**
 *
 * @author Lieven Lemiengre - Initial contribution and API
 * @since 2.1
 */
@ImplementedBy(SerialResourceLoader.class)
public interface IResourceLoader {

	/**
	 * Create a LoadOperation.
	 * Be careful, while the load operation is running the parent ResourceSet may be read from different threads.
	 * Synchronize on the parent ResourceSet when you interact with it!
	 *
	 * @param parent the parent ResourceSet
	 * @return a LoadOperation
	 */
	LoadOperation create(ResourceSet parent);

	/**
	 * The Interface LoadOperation.
	 */
	interface LoadOperation {

		/**
		 * Load the resources.
		 *
		 * @param uris the uris
		 * @throws IllegalStateException If the loading was already started or it was cancelled
		 */
		void load(Collection<URI> uris);

		/**
		 * Checks if the ResourceLoader is still processing resources.
		 *
		 * @return true, if successful
		 * @throws IllegalStateException If the loading was cancelled or it hasn't started yet.
		 */
		boolean hasNext();


		/**
		 * Get the next Resource.
		 * This is a blocking call, it returns the next resource that was finished processing.
		 *
		 * @return the next available resource
		 * @throws IllegalStateException If the loading was cancelled or it hasn't started yet.
		 * @throws LoadOperationException the load operation exception
		 */
		Resource next() throws LoadOperationException;

		/**
		 * Cancel loading the resources. This method can be executed multiple times.
		 *
		 * @return the collection
		 */
		Collection<URI> cancel();

	}

	@ImplementedBy(org.eclipse.xtext.builder.resourceloader.IResourceLoader.Sorter.NoSorting.class)
	interface Sorter {
		Collection<URI> sort(Collection<URI> uris);

		class NoSorting implements Sorter {
			public Collection<URI> sort(Collection<URI> uris) {
				return Lists.newArrayList(uris);
			}
		}
	}

	/**
	 * The Class LoadOperationException.
	 */
	class LoadOperationException extends WrappedException {

		private static final long serialVersionUID = 8499010336607816601L;

		private final URI uri;

		/**
		 * Instantiates a new load operation exception.
		 *
		 * @param uri the uri of the resource that failed to load
		 * @param exception the exception
		 */
		public LoadOperationException(URI uri, Throwable exception) {
			this(uri, new Exception(exception));
		}

		/**
		 * Instantiates a new load operation exception.
		 *
		 * @param uri the uri of the resource that failed to load
		 * @param exception the exception
		 */
		public LoadOperationException(URI uri, Exception exception) {
			super(exception);
			this.uri = uri;
		}

		/**
		 * Gets the URI of the resource that failed to load.
		 *
		 * @return the uri
		 */
		public URI getUri() {
			return uri;
		}

	}

}