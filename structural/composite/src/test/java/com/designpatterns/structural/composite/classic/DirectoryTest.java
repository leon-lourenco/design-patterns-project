package com.designpatterns.structural.composite.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectoryTest {

    @Test
    void aLeafFileReportsItsOwnNameAndSize() {
        FileLeaf file = new FileLeaf("readme.txt", 120);

        assertThat(file.name()).isEqualTo("readme.txt");
        assertThat(file.sizeBytes()).isEqualTo(120);
    }

    @Test
    void aFlatDirectoryReportsItsOwnNameAndSumsItsDirectChildren() {
        Directory root = new Directory("root");
        root.add(new FileLeaf("a.txt", 100));
        root.add(new FileLeaf("b.txt", 200));

        assertThat(root.name()).isEqualTo("root");
        assertThat(root.sizeBytes()).isEqualTo(300);
    }

    @Test
    void aNestedDirectoryTreeSumsRecursivelyThroughEveryLevel() {
        Directory root = new Directory("root");
        root.add(new FileLeaf("top.txt", 50));

        Directory subDir = new Directory("sub");
        subDir.add(new FileLeaf("nested1.txt", 30));
        subDir.add(new FileLeaf("nested2.txt", 20));

        Directory deeperDir = new Directory("deeper");
        deeperDir.add(new FileLeaf("deepest.txt", 10));
        subDir.add(deeperDir);

        root.add(subDir);

        assertThat(root.sizeBytes()).isEqualTo(50 + 30 + 20 + 10);
    }
}
