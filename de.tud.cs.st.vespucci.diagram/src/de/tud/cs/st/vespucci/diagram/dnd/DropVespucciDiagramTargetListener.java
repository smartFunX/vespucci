/*
 *  License (BSD Style License):
 *   Copyright (c) 2010
 *   Author MalteV
 *   Software Engineering
 *   Department of Computer Science
 *   Technische Universit�t Darmstadt
 *   All rights reserved.
 * 
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions are met:
 * 
 *   - Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   - Neither the name of the Software Engineering Group or Technische 
 *     Universit�t Darmstadt nor the names of its contributors may be used to 
 *     endorse or promote products derived from this software without specific 
 *     prior written permission.
 * 
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *   AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *   IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *   ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 *   LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *   CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *   SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *   INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *   CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *   ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *   POSSIBILITY OF SUCH DAMAGE.
 */
package de.tud.cs.st.vespucci.diagram.dnd;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;

/**
 * A listener for IResouce drops on the VespucciDiagram view
 * 
 * @author MalteV
 * 
 */
public class DropVespucciDiagramTargetListener extends AbstractTransferDropTargetListener {
	/**
	 * Constructor that set the drop type to IRecource
	 * 
	 * @param viewer
	 */
	public DropVespucciDiagramTargetListener(EditPartViewer viewer) {
		super(viewer, LocalSelectionTransfer.getTransfer());
	}

	protected void handleDragOver() {
		getCurrentEvent().detail = DND.DROP_COPY;
		super.handleDragOver();
		
	}

	protected void handleDrop() {
		getCurrentEvent().detail = DND.DROP_COPY;
		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
		//System.out.println(selection);
		Map<String, Object> m = new HashMap<String, Object>();
		for(Object o : ((TreeSelection) selection).toList())
			m.put(o.toString(), o);
		getTargetRequest().setExtendedData(m);
		// Save the IResources from the drop in a map for further tasks
		super.handleDrop();
	}

	@Override
	public boolean isEnabled(DropTargetEvent event) {
		return super.isEnabled(event);
	}

	@Override
	protected void updateTargetRequest() {
		((DirectEditRequest) getTargetRequest()).setLocation(getDropLocation());
		super.updateTargetEditPart();
	}

	@Override
	protected Request createTargetRequest() {
		DirectEditRequest request = new DirectEditRequest();
		return request;
	}



}