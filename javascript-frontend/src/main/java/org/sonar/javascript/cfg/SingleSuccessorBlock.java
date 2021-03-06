/*
 * SonarQube JavaScript Plugin
 * Copyright (C) 2011-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.javascript.cfg;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Set;

/**
 * A {@link MutableBlock} with exactly one successor.
 * SingleSuccessorBlocks which are empty (have no element) are removed from the graph
 * at the end of the construction of the graph.
 */
abstract class SingleSuccessorBlock extends MutableBlock {

  public abstract MutableBlock successor();

  @Override
  public MutableBlock trueSuccessor() {
    return successor();
  }

  @Override
  public MutableBlock falseSuccessor() {
    return successor();
  }

  @Override
  public Set<MutableBlock> successors() {
    Preconditions.checkState(successor() != null, "No successor was set on " + this);
    return ImmutableSet.of(successor());
  }

  public MutableBlock skipEmptyBlocks() {
    MutableBlock block = this;
    while (block instanceof SingleSuccessorBlock && block.isEmpty()) {
      block = ((SingleSuccessorBlock) block).successor();
    }
    return block;
  }

}
